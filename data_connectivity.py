import mysql.connector

mydatabase = mysql.connector.connect(
  host="localhost",
  user="root",
  password="root",
  database="try5",
  auth_plugin='mysql_native_password'
)

mycrursor = mydatabase.cursor()

###### return calories of a user ######
def get_calories(id):
    a = mycrursor.execute("SELECT calories from User_Info where Id = '"+str(id)+"'")
    p = mycrursor.fetchall()[0][0]
    return p

#### return food by its id #######
def get_food(id):
   mycrursor.execute("SELECT * FROM food_table where id = '"+str(id)+"'")
   return mycrursor.fetchall()



##### return a breakfast meal randomly #####
def get_breakfast_meal():
  mycrursor.execute("SELECT * FROM Breakfast_Meals2 ORDER BY RAND() LIMIT 1")
  return mycrursor.fetchall()

##### return food id and percent by breakfast_id #####
def get_food_breakast_id(id):
  mycrursor.execute("SELECT food_id,percentage FROM breakfast_food where breakfast_id = '"+str(id)+"'")
  return mycrursor.fetchall()

##### return a dinner meal randomly #####
def get_dinner_meal():
  mycrursor.execute("SELECT * FROM dinner_Meals ORDER BY RAND() LIMIT 1")
  return mycrursor.fetchall()

##### return food id and percent by dinner_id #####
def get_food_dinner_id(id):
  mycrursor.execute("SELECT food_id,percentage FROM dinner_food where dinner_id = '"+str(id)+"'")
  return mycrursor.fetchall()

##### return a lunch meal randomly #####
def get_lunch_meal():
  mycrursor.execute("SELECT * FROM lunch_Meals ORDER BY RAND() LIMIT 1")
  return mycrursor.fetchall()

##### return food id and percent by lunch_id #####
def get_food_lunch_id(id):
  mycrursor.execute("SELECT food_id,percentage FROM lunch_food where lunch_id = '"+str(id)+"'")
  return mycrursor.fetchall()

mydatabase.commit()