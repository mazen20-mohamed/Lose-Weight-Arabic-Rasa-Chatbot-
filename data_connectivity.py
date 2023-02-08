import mysql.connector
from datetime import date
import calendar
import pandas as pd
import numpy as np
mydatabase = mysql.connector.connect(
  host="localhost",
  user="Ebrahim",
  password="ebrahim",
  database="UserData",
  auth_plugin='mysql_native_password'
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

def get_calories(id):
    a = mycrursor.execute("SELECT calories from User_Info where Id = '"+str(id)+"'")
    p = mycrursor.fetchall()[0][0]
    return p
mydatabase.commit()

# def verfiy_login(email,password):
#   a = mycrursor.execute("SELECT * from Users where email = '"+email+"'")
#   return a
# def createUser(first_name,last_name,email,password):
#   sql = "INSERT INTO Users(first_name, last_name, email,password) VALUES ('"+first_name+"',"+"'"+last_name+"',"+"'"+email+"',"+"'"+password+"')"
#   mycrursor.execute(sql)
#  
