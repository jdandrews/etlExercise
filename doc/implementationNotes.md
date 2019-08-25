* When there is a regular or promotional split price, the associated “regular for X”/”promotional for X” value is the number 
of the split, E.g. reading the sample data (see below), product “14963801 Generic Soda 12-pack” has a regular price of 13.00 for 2, 
and a fixed promotional price of $5.49 each.
* Flags are read left-to-right, and numbering starting from 1. In the sample data, product “40123401 Marlboro Cigarettes” has 
flag #1 set and all others reset.

Output data types and mapping: 

| field                        | type   | notes                                                                       |
|------------------------------|--------|-----------------------------------------------------------------------------|
| Regular Display Price        | String | Format “$ ######.##” for regular pricing, or “$ ######.## / ########” for split pricing, or an empty string if not defined   |
| Regular Calculator Price     | Number | 4 places to the right of the decimal, fixed; zero if undefined. Always either per-unit or per-pound. |
| Promotional Display Price    | String | Same rules as regular display price                                         |
| Promotional Calculator Price | Number | Same rules as regular calculator price                                      |
| Unit of Measure              | String | Defined list; currently “Each” or “Pound”                                   |
| Product size                 | String | Only parsed for per-weight items; assuming it’s always “lb” in those cases. |
| Tax rate                     | Number |                                                                             |

Process flow:

1. store produces flat file (filename: store ID?)
2. flat file delivered to our input directory
3. directory scanner produces list of files, queues them to extractor queue
4. extractor pulls filename off the queue, reads a record at a time, and enqueues records to transformer queue
5. transformer reads records, identifies transform, produces output records, enqueues them to loader queue
6. loader reads and loads records