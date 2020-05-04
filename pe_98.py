##
#
# Project Euler 98
# https://projecteuler.net/problem=98
# By: Elias Lundell and Fredrik Lundell
#
##


with open("words.txt", "r") as f:
    words = list(map(lambda x: x[1:-1], f.read().split(",")))
    ind_words = list(zip(range(len(words)), words))

for i, word in enumerate(ind_words):
    ind_words[i] = [ind_words[i][0], ''.join(sorted(word[1]))]

ind_words.sort(key=lambda x: x[1])

anagrams = []

for i in range(len(ind_words) - 1):
    j = 1
    while i + j < len(ind_words) and ind_words[i][1] == ind_words[i + j][1]:
        anagrams.append([ind_words[i][0], ind_words[i + j][0]])
        j += 1

anagrams.sort(key=lambda x: len(words[x[0]]), reverse=True)

sorted_squares = list(zip(range(4, 100000), [''.join(sorted(str(k * k))) for k in range(4, 100000)]))
sorted_squares.sort(key=lambda x: x[1])

sqr_anagrams = []

for i in range(len(sorted_squares) - 1):
    j = 1
    while i + j < len(sorted_squares) and sorted_squares[i][1] == sorted_squares[i + j][1]:
        sqr_anagrams.append([sorted_squares[i][0], sorted_squares[i + j][0]])
        j += 1

sqr_anagrams.sort(key=lambda x: x[0] * x[0])

for anagram in anagrams:
    for number_anagram in sqr_anagrams:
        sqr_value = str(number_anagram[0] * number_anagram[0])
        if len(sqr_value) != len(words[anagram[0]]):
            continue
        assigned_letters = {}
        used_numbers = []
        used_letters = []
        for letter in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
            assigned_letters[letter] = -1

        br = False
        for ind, letter in enumerate(words[anagram[0]]):
            if assigned_letters[letter] != -1 and assigned_letters[letter] != sqr_value[ind]:
                br = True
                break
            if letter in used_letters:
                continue
            else:
                used_letters.append(letter)
            assigned_letters[letter] = sqr_value[ind]
            if sqr_value[ind] in used_numbers:
                br = True
                break
            else:
                used_numbers.append(sqr_value[ind])
        if br:
            continue

        sqr_value2 = str(number_anagram[1] * number_anagram[1])
        new_num = ""
        for letter in words[anagram[1]]:
            new_num += assigned_letters[letter]

        if new_num == sqr_value2:
            print(max(int(sqr_value), int(sqr_value2)))
            exit()


