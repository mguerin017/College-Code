import datetime

#Get user input variables
cap = float(input("Enter the battery capacity of your car in kWh."))
mile = float(input("Enter the mileage of your car in kWh/100 mi."))
voltage = float(input("Enter the output voltage of yor power socket in volts."))
current = float(input("Enter the output current of your power socket in amperes."))
percent = float(input("What percentage of your battery do you want to charge?"))

#Calculate final values
chargeTime = float((cap * (percent/100)) / ((voltage * current * 0.9)/1000))
chargeSpeed = float(((voltage * current * 0.9)/1000) * 100 / mile)
chargeCost = float(((cap * (percent/100)) * 0.104) / 0.9)

#Return information to user
print("It will take " + str(datetime.timedelta(hours=chargeTime)) + " to charge your car.")
print("You will gain", format(chargeSpeed,'.2f'), "miles per hour of charge.")
print("It will cost $", format(chargeCost,'.2f'), "to charge your car", format(percent,'.0f'), "%.")
