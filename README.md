# contacts project


Before run the program -> create a database for contacts project and configure the database options(address,port,username,password,url..) in application.properties file.

Contacts Controller ->

POST: 

    You can send POST request with List of ContactsRequest.\
    You can check ContactRequest example in 1.png\
    Phone format rules : Phone field in request must starts with "+countryCode" (example: +905554443322, +18085554466)\
    If phone of the request is turkish number(+90), it saves in db without the country code and blanks. (example: "+90553 264 42 56" -> "5532644256")\
    Phone saves in db without blanks. (example: "+1 800 80 80" -> "+18008080")\
    If there are same persons(same name&lastName) in request: 
    1) person already in db -> just phones are added to db (if phones are not already exist for same person)\
    2) person doesn't exist -> new record is created one time, and phones are merged in this record.\

GET: 

    You can search records by name with this request.\
    You can check example in 2.png\
    If there is not a record with name that you search, program throws NameNotFoundException\

FileController->\
POST: 	 

    You can POST data with txt file.(format: name,surname,phone)
    It works with Postman.\
    You can check postman screenshot in 3.png\

I used swagger open api for documentation. After run the program you can check it with the link below;\
http://localhost:7010/contacts/api/v1/swagger-ui/index.html
