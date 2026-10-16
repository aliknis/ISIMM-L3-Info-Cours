def vigenere_decode(texte, cle):
    result = ""
    cle = cle.upper()  # Uniformise la clé en majuscules
    j = 0
    for c in texte:
        if c.isalpha():  # Vérifie si c'est une lettre
            base = 65 if c.isupper() else 97  # Majuscule (A=65) ou minuscule (a=97)
            decalage = ord(cle[j % len(cle)]) - 65  # Décalage basé sur la lettre de la clé
            result += chr((ord(c) - decalage - base) % 26 + base)
            j += 1  # Passe à la lettre suivante de la clé
        else:
            result += c  # Garde les espaces ou autres caractères
    return result

# Exemple Mission Impossible
print(vigenere_decode("", ""))   # completer par votre message chiffre et votre cle 

