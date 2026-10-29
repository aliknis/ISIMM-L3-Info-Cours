import sys
import os

try:
    clef = os.environ["KEY"]
except:
    print("Unknown encryption key")
    exit()

for line in sys.stdin:
    line = line[:-1]
    line = line.upper()

    key = ""
    lc = len(clef)
    lm = len(line)

    for i in range(lm):
        key += clef[i % lc]

    messageCrypt = ""
    for i in range(lm):
        if line[i] == " ":
            messageCrypt += " "
        else:
            step = ord(key[i]) - ord("A")
            # print(step)
            indice = (ord(line[i]) - ord("A") + step) % 26
            messageCrypt += chr(indice + ord("A"))

    print(messageCrypt)
