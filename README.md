# EssaiSansValeur
just for trying


La description du fonctionnement est cla suivante : 
- le client envoie deux fois la même chose avec deux lib JSON différentes puis d'arrête 
- le serveur reçoit

coté serveur :
couleur recue : couleur [rouge = 124 / vert = 254 bleu = 21]
couleur recue : couleur [rouge = 124 / vert = 254 bleu = 21]

coté client :
j'envoie avec simplejson : {"bleu":21,"vert":254,"rouge":124} pour couleur [rouge = 124 / vert = 254 bleu = 21]
avec org.json : {"bleu":21,"vert":254,"rouge":124}
termine 

il y a différents lanceurs (avec id pour mvn exec:java@id pour lancer 2 (par défaut), 5 (id cinq) ou 10 (id dix) clients
