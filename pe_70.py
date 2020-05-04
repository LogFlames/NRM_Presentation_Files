##
#
# Project Euler 70
# https://projecteuler.net/problem=70
# By: Elias Lundell
#
##

import math

def primes_under_n(n):
    primes = [2]
    pot_primes = [x for x in range(3, n, 2)]
    sqrt_n = math.sqrt(n)

    selected = 0

    while pot_primes[selected] < sqrt_n:
        if pot_primes[selected] == -1:
            selected += 1
            continue
        primes.append(pot_primes[selected])
        m = pot_primes[selected]
        for i in range(selected + m, len(pot_primes), m):
            pot_primes[i] = -1

        selected += 1

    for i in range(selected, len(pot_primes)):
        if pot_primes[i] != -1:
            primes.append(pot_primes[i])

    return primes

primes = primes_under_n(10**7 // 2 + 1)

def prime_factorize(n):
    factors = []
    sqrt_n = math.sqrt(n)
    for prime in primes:
        if n % prime == 0:
            factors.append(prime)
            n //= prime
        while n % prime == 0:
            n //= prime
        if n == 1:
            break
        if prime > sqrt_n:
            factors.append(n)
            break
    return factors

def totient(n):
    primesDiv = prime_factorize(n)
    # The number times (1 - 1 / p) for all it's distincte prime factors
    # Expl
    # We start with n numbers smaller than n
    # Then if we have a factor p, every p number is not coprime (n / p), aka we multiply with (1 - 1 / p) as we have everything but those n * (1 / p) left
    prod = n
    for fact in primesDiv:
        prod *= (fact - 1) / fact
    return int(prod)

def is_permutation(a, b):
    a = str(a)
    b = str(b)
    while len(a) != 0 and len(b) != 0:
        s = a[0]
        if s in b:
            a = a[1:]
            b = b.replace(s, "", 1)
        else:
            return False

    return a == "" and b == ""

def main():
    smallestRat = 10**7 + 1
    saveN = -1

    for N in range(2, 10**7):
        if N % 100000 == 0:
            print("{}%".format(100 * N // 10**7))
        tot = totient(N)
        if is_permutation(tot, N):
            rat = N / tot
            if rat < smallestRat:
                smallestRat = rat
                saveN = N

    print(saveN)

if __name__ == "__main__":
    main()
