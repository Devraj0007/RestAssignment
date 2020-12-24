# RestAssignment

# Find the Details of Method name and Request and Response for (Create,Update,Delete,retrieve and Count)

# To retrieve all the person Details

Method Name:--- GET

Request Url: http://localhost:8080/api/persons

Response:

[
  {
    "id": 1,
    "firstName": "Devraj",
    "lastName": "Prakesh",
    "emailId": "devraj@gmail.com"
  },
  {
    "id": 2,
    "firstName": "Jai",
    "lastName": "Kumar",
    "emailId": "jai@gmail.com"
  }
  ]

**************************************************
# To retrieve single person Details Based on Id

Method Name:--- GET

Request Url: http://localhost:8080/api/persons/{id}

Response:

{
    "id": 1,
    "firstName": "Devraj",
    "lastName": "Prakesh",
    "emailId": "devraj@gmail.com"
  }
  
****************************************************
# To Insert the Person record 

Method Name:----- POST
Request Url: http://localhost:8080/api/persons

Request Body:

{
  "emailId": "devraj@gmail.com",
  "firstName": "Devraj",
  "lastName": "Prakash"
}
Response:

{
  "id": 6,
  "firstName": "Devraj",
  "lastName": "Prakash",
  "emailId": "devraj@gmail.com"
}

*****************************************************

# To Update person Details Based on Id

Method Name:-----PUT
Request Url:  http://localhost:8080/api/persons/{id}

Request Body:
{
  "emailId": "test@Gmail.com",
  "firstName": "Prakash",
  "lastName": "Devraj"
}

Response:

{
  "id": 3,
  "firstName": "Prakash",
  "lastName": "Devraj",
  "emailId": "test@Gmail.com"
}

*****************************************************

# Delete person Details Based on Id

Method Name:----- DELETE

Request Url: http://localhost:8080/api/persons/{id}

Response:

{
  "deleted": true
}

****************************************************

# Count the total number of persons

Method Name:----- GET

Request Url:  http://localhost:8080/api/personCount

Response:

6

***************************************************

# I have Used H2 database in Backed, so there is no any physical installation required.

# SWAGGER Url
 
http://localhost:8080/swagger-ui.html 



