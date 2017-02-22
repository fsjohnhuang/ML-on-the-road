Files may be written by one writer, and writes always to made in the end of file(i.e in append-only fashion).There is no support for multiple writers or modifications at the arbitrary offsets in the file. 

## Block
  Block is the minimum storage unit of HDFS to read/write whose default size is 128MB. However the block of filesystem is much smaller, which is `n*512Byte`.<br>
  The size of block of filesystem or HDFS is the block of disk by the factor of n generally(i.e. `n*512Byte`).<br>
### The difference between filesystem block and HDFS block
  a single block of filesystem would occupy the fully block's worth of underlying storage(e.g. disk, usb), but HDFS does not. It means a 1MB file stored with a block size of 128MB uses 1MB of disk space,not 128MB.<br>
### The benifit from large block
```
BLOCK_SIZE    SEEK_COST    TRANSFER_RATE
small         high         fast
big           low          slow
```
But the transfer rate grows with the new generation of disk driver.

```
hdfs fsck / -files -blocks
```

## Namenode and Datanode
According to master-worker pattern, Namenode acts as master, and Datanode do as worker.<br>
Namenode is stored persistently in 2 forms: namespace image, edit log.<br>
Namenode knows the Datanodes on which all block for a given file are located. But don't store the block locations persistently.<br>
### Secondary Namenode
Secondary Namenode is to merge the namespace image with edit log to prevent the edit log from becoming large, and keep the copy the merged namespace image which will be used in the event of the primary failing. Howere the secondary namenode lags that the primary of, loss data is certain.

### Namespace image
### Edit log

## Block Caching

Job Scheduler:MapReduce, Spark and so on.
