import mysql.connector

mydatabase = mysql.connector.connect(
  host="localhost",
  user="root",
  password="root",
  database="UserData"
)

mycrursor = mydatabase.cursor()

# mycrursor.execute("CREATE TABLE users (id int AUTO_INCREMENT PRIMARY KEY, fisrt_name VARCHAR(255) , last_name VARCHAR(255) , email varchar(255) , password varchar(255) NOT NULL)")
# string = "INSERT INTO Users(fisrt_name, last_name, email,password) VALUES ('"+first_name+"',"+"'"+last_name+"',"+"'"+email+"',"+"'"+password+"')"
# def return_name():
#   string = "SELECT fisrt_name FROM Users"
#   mycrursor.execute(string)
#   p = mycrursor.fetchall()[0][0]
#   print(p)
#   return p

mydatabase.commit()

# def verfiy_login(email,password):
#   a = mycrursor.execute("SELECT * from Users where email = '"+email+"'")
#   return a
# def createUser(first_name,last_name,email,password):
#   sql = "INSERT INTO Users(first_name, last_name, email,password) VALUES ('"+first_name+"',"+"'"+last_name+"',"+"'"+email+"',"+"'"+password+"')"
#   mycrursor.execute(sql)
#  
