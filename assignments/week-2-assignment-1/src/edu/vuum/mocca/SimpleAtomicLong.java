package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

/**
 * @class SimpleAtomicLong
 *
 * @brief This class implements a subset of the
 *        java.util.concurrent.atomic.SimpleAtomicLong class using a
 *        ReentrantReadWriteLock to illustrate how they work.
 */
class SimpleAtomicLong
{
    /**
     * The value that's manipulated atomically via the methods.
     */
    private long mValue;

    /**
     * The ReentrantReadWriteLock used to serialize access to mValue.
     */
    // TODO - add the implementation
    private final ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock();

    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue)
    {
        // TODO - you fill in here
      mValue = initialValue;
    }

    /**
     * @brief Gets the current value.
     *
     * @returns The current value
     */
    public long get()
    {
        long value;

        // TODO - you fill in here
      mRWLock.readLock().lock();
      value = mValue;
      mRWLock.readLock().unlock();

        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the updated value
     */
    public long decrementAndGet()
    {
        long value = 0;

        // TODO - you fill in here
      value = alterAndGet(-1);

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the previous value
     */
    public long getAndIncrement()
    {
        long value = 0;

        // TODO - you fill in here
      value = getAndAlter(1);

        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the previous value
     */
    public long getAndDecrement()
    {
        long value = 0;

        // TODO - you fill in here
      value = getAndAlter(-1);

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the updated value
     */
    public long incrementAndGet()
    {
        long value = 0;

        // TODO - you fill in here
       value = alterAndGet(1);

        return value;
    }

  private long alterAndGet(long l) {
    try {

      mRWLock.writeLock().lock();

      return mValue += l;

    } finally {

      mRWLock.writeLock().unlock();

    }
  }

  private long getAndAlter(long l) {

    try {

      mRWLock.writeLock().lock();

      long value = mValue;
      mValue += l;
      return value;

    } finally {

      mRWLock.writeLock().unlock();

    }

  }

}

