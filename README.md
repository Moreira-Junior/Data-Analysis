## Tech Challenge - ilegra

Hello, I'm Moreira-Junior and that's my solution for the Data Analysis System Challenge.

### Requirements

 - Java 11+

### Running

You just have to clone this repo and then open a terminal inside the clone path and execute the following command:

```
./gradlew run
```

Now you can observe that 5 directories were created on the path $HOME/data:
 - in - where your data files go and the application will monitor for new files
 - out - where the result data files go, after being processed
 - proc - where the files successfully processed go, moved from in to proc
 - invalid - where the files unsuccessfully processed go, moved from in to invalid
 - log - where the logs are kept

### How is the data?

There are 3 kinds of data inside those files. For each kind of data there is a different layout.

 - Salesman data

Salesman data has the format id 001 and the line will have the following format.
```
001çCPFçNameçSalary
```

 - Customer data

Customer data has the format id 002 and the line will have the following format.

```
002çCNPJçNameçBusinessArea
```

 - Sales data

Sales data has the format id 003. Inside the sales row, there is the list of items, which is
wrapped by square brackets <b>[ ]</b>. The line will have the following format.

```
003çSaleIDç[ItemID-ItemQuantity-ItemPrice]çSalesmanname
```

### Data Analysis

The output file contents should summarize the following data:

 - Amount of clients in the input file
 - Amount of salesman in the input file
 - ID of the most expensive sale
 - Worst salesman ever

### Result Structure

For the following data

```
001ç12234567891234çDiegoç50000 001ç3245678865434çRenatoç40000.99
002ç2345675433444345çJosedaSilvaçRural
002ç23çEduardoPereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato
```

a result will be generated as below:

```
Output{timestamp=2022-07-27 08:02:39.901, fileName='temp1.dat', 
amountOfClients=2, amountOfSalesmen=2, mostExpensiveSale='10', 
worstSalesmanEver='Renato'}
```
With a timestamp, the name of the file processed, amount of clients in the
file, amount of salesmen in the file, id of the most expensive sale of all
processed files and the name of the worst salesman ever.

Logs will look like this in case of success:

```
Jul 27, 2022 8:02:33 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
INFO: Program started!
Jul 27, 2022 8:02:39 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
INFO: Read file: temp1.dat
Jul 27, 2022 8:02:39 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
INFO: Output created: temp1.done.dat
```

Also like this in case of invalid data input:
```
Jul 27, 2022 8:02:33 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
INFO: Program started!
Jul 27, 2022 8:02:39 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
INFO: Read file: temp1.dat
Jul 27, 2022 8:02:39 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
INFO: Output created: temp1.done.dat
Jul 27, 2022 8:06:01 AM com.MoreiraJunior.desafio.processors.InputProcessor treatFile
SEVERE: Error during formatting
Jul 27, 2022 8:06:01 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
WARNING: Could not read file: temp6.dat
Jul 27, 2022 8:06:20 AM com.MoreiraJunior.desafio.processors.InputProcessor treatFile
SEVERE: Data in this file is compromised!
Jul 27, 2022 8:06:20 AM com.MoreiraJunior.desafio.watcher.WatchFile watch
WARNING: Could not read file: temp4.dat
```