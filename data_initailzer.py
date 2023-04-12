import mysql.connector
from datetime import date
import calendar
import pandas as pd
import numpy as np
mydatabase = mysql.connector.connect(
  host="localhost",
  user="root",
  password="root",
  database="try5",
  auth_plugin='mysql_native_password'
)

mycrursor = mydatabase.cursor()


################################################ table creations #######################################
# mycrursor.execute("CREATE TABLE weight_table (id int AUTO_INCREMENT PRIMARY KEY, user_id int NOT NULL, time_taken DATETIME NOT NULL , weight DOUBLE NOT NULL, constraint FK1 FOREIGN KEY (user_id) REFERENCES person(id))")
# mycrursor.execute("CREATE TABLE Login_table (id int AUTO_INCREMENT PRIMARY KEY, user_id int NOT NULL, login_time DATETIME NOT NULL , constraint FK FOREIGN KEY (user_id) REFERENCES person(id))")
#mycrursor.execute("CREATE TABLE food_table (id int AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) ,calory DOUBLE , carb DOUBLE , fat DOUBLE ,proten DOUBLE , size DOUBLE ,type_size VARCHAR(255))")
#mycrursor.execute("CREATE TABLE Breakfast_Meals2 (id int AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255),link VARCHAR(255) )")
#mycrursor.execute("CREATE TABLE breakfast_food (id int AUTO_INCREMENT PRIMARY KEY, breakfast_id int , food_id int,percentage int ,constraint FK9 FOREIGN KEY (breakfast_id) REFERENCES Breakfast_Meals2(id) ,constraint FK10 FOREIGN KEY (food_id) REFERENCES food_table(id)  )")
#mycrursor.execute("CREATE TABLE dinner_Meals (id int AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255),link VARCHAR(255) )")
#mycrursor.execute("CREATE TABLE dinner_food (id int AUTO_INCREMENT PRIMARY KEY, dinner_id int , food_id int,percentage int ,constraint FK11 FOREIGN KEY (dinner_id) REFERENCES dinner_Meals(id) ,constraint FK12 FOREIGN KEY (food_id) REFERENCES food_table(id)  )")
#mycrursor.execute("CREATE TABLE lunch_Meals (id int AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255),link VARCHAR(1000))")
#mycrursor.execute("CREATE TABLE lunch_food (id int AUTO_INCREMENT PRIMARY KEY, lunch_id int , food_id int,percentage int ,constraint FK13 FOREIGN KEY (lunch_id) REFERENCES lunch_Meals(id) ,constraint FK14 FOREIGN KEY (food_id) REFERENCES food_table(id)  )")



################################################### add data to table ########################################
def add_food_database():
    dataframe1 = pd.read_excel('food_table.xlsx')
    data = np.array(dataframe1)
    m = len(data)
    for i in range(m):
      string = "INSERT INTO food_table(name,calory,carb,fat,proten,size,type_size) VALUES ('"
      for j in range(7):
        string +=str(data[i][j])
        if j<6:
           string+="','"
        else:
           string+="')"
      mycrursor.execute(string)     

def add_breakfast_Meals():
    dataframe2 = pd.read_excel('Breakfast_Meals.xlsx')
    dataframe1 = pd.read_excel('Breakfast_food.xlsx')
    data = np.array(dataframe1)
    data2 = np.array(dataframe2)
    m2 = len(data2)
    for i in range(m2):
        string = 'INSERT INTO Breakfast_Meals2(name,link) VALUES ("{0}","{1}")'.format(data2[i][0],data2[i][1])
        mycrursor.execute(string) 
    m = len(data)
    for i in range(m):
      string = 'INSERT INTO breakfast_food(breakfast_id,food_id,percentage) VALUES ("{0}","{1}","{2}")'.format(data[i][0],data[i][1],data[i][2])
      mycrursor.execute(string)  
       
def add_dinner_Meals():
    dataframe2 = pd.read_excel('dinner_meals_link.xlsx')
    dataframe1 = pd.read_excel('dinner_meals.xlsx')
    data = np.array(dataframe1)
    data2 = np.array(dataframe2)
    m2 = len(data2)
    for i in range(m2):
        string = 'INSERT INTO dinner_Meals(name,link) VALUES ("{0}","{1}")'.format(data2[i][0],data2[i][1])
        mycrursor.execute(string) 
    m = len(data)
    for i in range(m):
      string = 'INSERT INTO dinner_food(dinner_id,food_id,percentage) VALUES ("{0}","{1}","{2}")'.format(data[i][0],data[i][1],data[i][2])
      mycrursor.execute(string)  

def add_lunch_Meals():
    dataframe2 = pd.read_excel('lunch_meals.xlsx')
    dataframe1 = pd.read_excel('lunch_food.xlsx')
    data = np.array(dataframe1)
    data2 = np.array(dataframe2)
    m2 = len(data2)
    for i in range(m2):
        string = 'INSERT INTO lunch_Meals(name,link) VALUES ("{0}","{1}")'.format(data2[i][0],data2[i][1])
        mycrursor.execute(string) 
    m = len(data)
    for i in range(m):
      string = 'INSERT INTO lunch_food(lunch_id,food_id,percentage) VALUES ("{0}","{1}","{2}")'.format(data[i][0],data[i][1],data[i][2])
      mycrursor.execute(string)  

#################################################function call to excute ######################################
#add_food_database()
# add_lunch_Meals()
# add_dinner_Meals()
# add_breakfast_Meals()
mydatabase.commit()