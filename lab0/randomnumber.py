import random

with open('randomnumber.txt','a') as file:
    for i in range(1000000):
        file.write(f"{random.randint(0, 100000)} ")