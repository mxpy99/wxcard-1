package com.wxccase.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class IdUtil {
	 private final long twepoch = 1288834974657L;
	  private final long workerIdBits = 5L;
	  private final long datacenterIdBits = 5L;
	  private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
	  private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	  private final long sequenceBits = 12L;
	  private final long workerIdShift = sequenceBits;
	  private final long datacenterIdShift = sequenceBits + workerIdBits;
	  private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	  private final long sequenceMask = -1L ^ (-1L << sequenceBits);
	 
	  private long workerId = 0;
	  private long datacenterId = 0;
	  private long sequence = 0L;
	  private long lastTimestamp = -1L;
	 
	  public IdUtil(){}
	  
	  public IdUtil(long workerId, long datacenterId) {
	    if (workerId > maxWorkerId || workerId < 0) {
	      throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
	    }
	    if (datacenterId > maxDatacenterId || datacenterId < 0) {
	      throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
	    }
	    this.workerId = workerId;
	    this.datacenterId = datacenterId;
	  }
	 
	  protected long tilNextMillis(long lastTimestamp) {
	    long timestamp = timeGen();
	    while (timestamp <= lastTimestamp) {
	      timestamp = timeGen();
	    }
	    return timestamp;
	  }
	 
	  protected long timeGen() {
	    return System.currentTimeMillis();
	  }
	  
	  public synchronized long nextUserId() {
		    long timestamp = timeGen();
		    if (timestamp < lastTimestamp) {
		      throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		    }
		    if (lastTimestamp == timestamp) {
		      sequence = (sequence + 1) & sequenceMask;
		      if (sequence == 0) {
		        timestamp = tilNextMillis(lastTimestamp);
		      }
		    } else {
		      sequence = 0L;
		    }
		 
		    lastTimestamp = timestamp;
		    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	  }
	  public synchronized long nextFollowId() {
		    long timestamp = timeGen();
		    if (timestamp < lastTimestamp) {
		      throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		    }
		    if (lastTimestamp == timestamp) {
		      sequence = (sequence + 1) & sequenceMask;
		      if (sequence == 0) {
		        timestamp = tilNextMillis(lastTimestamp);
		      }
		    } else {
		      sequence = 0L;
		    }
		 
		    lastTimestamp = timestamp;
		    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	  }
	  public synchronized long nextClassifyId() {
		    long timestamp = timeGen();
		    if (timestamp < lastTimestamp) {
		      throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		    }
		    if (lastTimestamp == timestamp) {
		      sequence = (sequence + 1) & sequenceMask;
		      if (sequence == 0) {
		        timestamp = tilNextMillis(lastTimestamp);
		      }
		    } else {
		      sequence = 0L;
		    }
		 
		    lastTimestamp = timestamp;
		    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	  }
		  
	  public synchronized long nextCardinfoId() {
		    long timestamp = timeGen();
		    if (timestamp < lastTimestamp) {
		      throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		    }
		    if (lastTimestamp == timestamp) {
		      sequence = (sequence + 1) & sequenceMask;
		      if (sequence == 0) {
		        timestamp = tilNextMillis(lastTimestamp);
		      }
		    } else {
		      sequence = 0L;
		    }
		 
		    lastTimestamp = timestamp;
		    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	  }
		  
	  public synchronized long nextFaqId() {
		    long timestamp = timeGen();
		    if (timestamp < lastTimestamp) {
		      throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		    }
		    if (lastTimestamp == timestamp) {
		      sequence = (sequence + 1) & sequenceMask;
		      if (sequence == 0) {
		        timestamp = tilNextMillis(lastTimestamp);
		      }
		    } else {
		      sequence = 0L;
		    }
		 
		    lastTimestamp = timestamp;
		    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	 }
		  
	 public synchronized String nextSessionId(){
			return UUID.randomUUID().toString().replace("-", "");
		}
}
