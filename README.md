# Palette-Finder
"Find your palette !"

Que ce soit des dessins 2D aux modèles 3D, les palettes de couleurs sont essentielles dans le domaine artistique, et encore plus dans l’univers virtuel. Il est donc toujours utile d’avoir un logiciel capable d’obtenir des palettes harmonisées.

Palette Finder permet d’obtenir rapidement une palette de 5 couleurs harmonisées pour des projets artistiques grâce à l’API ColorMind : http://colormind.io/api-access/

Grâce à Palette Finder, l’utilisateur peut aisément obtenir une palette de couleur harmonisé, et ce de manière totalement aléatoire ou en spécifiant certaines couleurs voulues (données à rentrer à la main, cf. descriptif API).

De plus, l’application sauvegarde ses palettes précédentes dans une base de données locale, afin de pouvoir les retrouver aisément : pour s’y retrouver, l’utilisateur peut leur donner un nom.

# Descriptif base de donnée :

La base de données locale a pour but d’assurer la sauvegarde des palettes obtenues pour l’utilisateur. La base de donnée créée sera donc sous le format suivant :

- paletteName : String : nom de la palette, rentré par l’utilisateur (si pas de nom rentré, mettre nom générique)
- color1 : [int, int, int] : valeurs RGB de la couleur 1
- color2 : [int, int, int] : valeurs RGB de la couleur 2
- color3 : [int, int, int] : valeurs RGB de la couleur 3
- color4 : [int, int, int] : valeurs RGB de la couleur 4
 -color5 : [int, int, int] : valeurs RGB de la couleur 5

# Requête envoyée :

La requête envoyée à l’API est sous le format :

curl 'http://colormind.io/api/' 
--data-binary '{"input":[[44,43,44],[90,83,82],"N","N","N"],"model":"default"}'

Où “N” est lorsque l’on demande une couleur aléatoire et [int,int,int] lorsque l’on demande une couleur précise, et ce pour chaque couleur.
