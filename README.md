## Clone the project from git
https://github.com/collinssang/ipslRepo.git

open from any ide of your choice preferably intellij (used during development)

let it load all the maven dependencies and then you can run the project. 

the tomcat port that will be exposed is 8080 
hence from api tester i.e postman you can test all the 
endpoints in the project from 
# user creation, 
# login and 
# fetching tasks, 
# paged tasks, 
# Creating new tasks, 
# updating tasks, 
# deleting tasks,


The collection of the endpoint is at the root of the project folder. import it to your postman 
and run the collection

from the cloned repo execute the sql file in the root of the project folder
after creating your preferred db. or simply create DB in your mysql server
then go to application.properties to set the db connections configuration details
run the project and the hibernate will create the tables on its own. 

Regards, 

Sang Collins,

kipkuruicollinssang@gmail.com

Good Bye. 