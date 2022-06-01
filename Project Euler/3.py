# The prime factors of 13195 are 5, 7, 13 and 29.
# What is the largest prime factor of the number 600851475143?

def isPrime(factor):
    for x in range(2,factor):
        if factor % x == 0:
            return False
    else:
        return True

num = 600851475143
factor = 2
print("Please wait...")
while factor <= num:
    if num % factor == 0 and isPrime(factor) == True:
        largest = factor
    factor += 1
print(largest)
