project status:
[![Build status](https://ci.appveyor.com/api/projects/status/8rtrg4ca8na9qlgv?svg=true)](https://ci.appveyor.com/project/jdandrews/etlexercise) 
master branch status:
[![Build status](https://ci.appveyor.com/api/projects/status/8rtrg4ca8na9qlgv/branch/master?svg=true)](https://ci.appveyor.com/project/jdandrews/etlexercise/branch/master)

#Swiftly sample ETL project

This is a little ETL project; the description is in the docs directory
(see [./docs/ProductInformationIntegrationSpec.md]()). To execute it, 
    
    gradle run

This executes the "Main" class, which launches the server. Ctrl-C to exit.

The server creates 3 directories: etlIncoming, etlWork, and etlComplete. Push a data
file into etlIncoming, and the server will pick it up, transform it, and list the
records it finds to STDOUT. During processing, the file will move to the etlWork
directory; when processing is complete, it moves to the etlComplete directory.

There's a small sample file in src/test/resources. You can copy that into the 
etlIncoming directory if you want to see it run.

This is not production-ready code. It lacks error handling and a few places aren't
thread-safe (that is, they'll crash with NPEs if you change the number of threads
used in the Main queue setup).  I'd need another pass through the code to handle
those cases, and I may do it someday, but not today.