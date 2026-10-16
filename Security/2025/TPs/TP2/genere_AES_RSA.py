
import pyzipper
import os
from subprocess import run

# Messages secrets 
secrets = [
    "Ethan Hunt : Craquez ce code pour rejoindre l'IMF !",
    "Mission : Infiltrez le serveur avec ce secret.",
    "Tom Cruise : La clé est dans la passphrase.",
    "Gadget IMF : Déverrouillez pour activer.",
    "Ordre : Évitez l'autodestruction !",
    "Briefing : Trouvez la cible avec ce code.",
    "Agent secret : La mission commence maintenant.",
    "Helicoptère : Le code déverrouille l'évasion.",
    "Explosion : Dépêchez-vous avant le boom !",
    "Double agent : Ce message révèle la vérité."
]

# Dossier pour sauvegarder les fichiers
output_dir = "mission_impossible_files"
os.makedirs(output_dir, exist_ok=True)

# Générer 10 fichiers ZIP chiffrés avec AES-256
for i in range(1, 11):
    zip_name = os.path.join(output_dir, f"secret_groupe{i}_aes.zip")
    pwd = f"mission{i:03d}"  
    with pyzipper.AESZipFile(zip_name, 'w', compression=pyzipper.ZIP_LZMA, encryption=pyzipper.WZ_AES) as zf:
        zf.setpassword(pwd.encode())  # Mot de passe pour chiffrement AES-256
        zf.writestr("secret.txt", secrets[i-1])
    print(f"Groupe {i} AES : {zip_name}")

# Générer 10 clés RSA privées chiffrées
for i in range(1, 11):
    key_name = os.path.join(output_dir, f"id_rsa_groupe{i}")
    passphrase = f"impossible{i:03d}" 
    cmd = f"ssh-keygen -t rsa -b 2048 -f {key_name} -N '{passphrase}' -q"
    run(cmd, shell=True, check=True)
    print(f"Groupe {i} RSA : {key_name}")
