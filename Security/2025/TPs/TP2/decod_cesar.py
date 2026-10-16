def cesar_decode(texte, decalage):
    result = ""
    for c in texte:
        if c.isalpha():  # Vérifie si c'est une lettre
            base = 65 if c.isupper() else 97  # Majuscule (A=65) ou minuscule (a=97)
            result += chr((ord(c) - decalage - base) % 26 + base)
        else:
            result += c  # Garde les espaces ou autres caractères
    return result


print(cesar_decode("", ))  # completer par votre message chiffre et votre cle 

