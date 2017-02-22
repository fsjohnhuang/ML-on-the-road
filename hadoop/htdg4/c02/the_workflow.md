# The Workflow of MapReduce Job
```
INPUT DATA
--split--> SPLITS
--map--> OUTPUT of MAP
[
	[--combine--> OUTPUT of COMBINE]
	--shuffle--> OUTPUT SORTED by KEY
	[--partitionning--> OUTPUT of PARTITION]
	--reduce--> OUTPUT of REDUCE
]
```
**MapReduce job** is a unit of work that client wants to be performed.It consists of
1. input data
2. MapReduce program
3. configuration information
A job instance would be divided into **map task**s and **reduce task**s. So the relationship is like below:
```
1 job <--> n map tasks
1 job <--> n reduce tasks
```

## Step Explaination
1. split<br>
  Input data would be splited into fixed-size chunks called **input splits** or **splits**.<br>
  Each of splits is correspond to a mapper instance.<br>
  The ideal size of split is as the same as the HDFS block(i.e. 128MB). Hadoop could call mapper function without network transfer, because of that DataNode could store a HDFS block at least.<br>
  Each of records of split would pass to the map function instance.<br>
2. map
  Map function would extract the fields that you're intrested, and emit them as output.<br>
  The output of map is store in the OS filesystem other than HDFS temporarily.<br>
3. combine
  _Optional Step._<br>
  Just like the reduce function, but invoked on the DataNodes with map function. Hadoop would call combine function one or many times, or zero even, no guarentee.<br>
```
-- for example, reduce function is the left side, combine function is the inner max of the right side expression.
max([1,2,3,4]) = max(max([1,2]),max(3,4))
```
4. shuffle
  After performing map functions on DataNodes parallel, Hadoop wolud transfer the output of mappers to the node to perform reduce function.And before call reduce function, the input of reduce function would be sorted by key.
5. partitionning
  A kind of key is correspond to one reduce function instance intuitive. But that is not the truth.We could split the output of shuffle to multiple reduce function by partitionnign functions.
6. reduce
  The last step of MapReduce job.<br>
  The output of ruduce is store in the HDFS.<br>
