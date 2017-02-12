`sample1.md`,事例１－统计各店销售总额<br>

## MapReduce Pattern
### Filtering Patterns
Sampling
Top-N<br>
1.每个mapper产生一个Top-N列表；<br>
2.reducer接收mapper传来的多个Top-N列表，再产生一个全局的Top-N列表．<br>
### Summarization Patterns
Counting
Min/Max
Statistics
Index
### Structural Patterns
Combining data set
