##
#
# Project Euler 87
# https://projecteuler.net/problem=87
# By: Elias Lundell
#
##

import math

def generate_primes(n):
    potPrimes = [i for i in range(3, n, 2)]
    primes = [2]

    sqrt_n = int(math.sqrt(n))

    for i in range(sqrt_n):
        if potPrimes[i] != -1:
            primes.append(potPrimes[i])
            for apix in range(i + potPrimes[i], len(potPrimes), potPrimes[i]):
                potPrimes[apix] = -1

    for i in range(sqrt_n, len(potPrimes)):
        if potPrimes[i] != -1:
            primes.append(potPrimes[i])

    return primes

primes = generate_primes(int(math.sqrt(50000000)))

nums = set()

for a in primes:
    for b in primes:
        ab = a * a + b * b * b
        if ab > 50000000:
            break
        for c in primes:
            num = ab + c * c * c * c 
            if num <= 50000000:
                nums.add(num)
            else:
                break

print(len(nums))
