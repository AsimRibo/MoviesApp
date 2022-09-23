# MoviesApp
Movies application that displays information of soon to play or playing movies in Cinestar and allows CRUD operations on them.
Default login credentials:
- username: admin
- password: admin

UPDATE: rss feed is no longer available :(

## Short description
- Built in java 8 and uses Swing for GUI purposes.
- Uses rss parser to parse following link: [CinestarRSS](https://www.blitz-cinestar.hr/rss.aspx). Rss data might be broken sometimes but that's due to rss not having a set rules for data they provide.
- Project consists of 2 main views, a view for admins that take care of the application and users, and view for basic users to see movies info.
- Uses JAXB for saving movies in XML file

## Login screen
![Login](https://user-images.githubusercontent.com/84510840/168162328-81f99443-0c98-448f-9d48-67f70cf1708e.png)
\
## Admin views
- View for managing movies
![adminMovies](https://user-images.githubusercontent.com/84510840/168162904-3cd1a968-c8a2-447d-a0a1-b485aa0a4041.png)

</br>

- View for managing registered users
 ![adminUsers](https://user-images.githubusercontent.com/84510840/168162954-ced7ece4-95d0-475b-b323-4e458e7c0741.png)

## User views
- View displaying movies and allowing custom changes to movie
 ![userMovies](https://user-images.githubusercontent.com/84510840/168163038-6f949ba9-865b-4736-9bc6-bffa4a8ff128.png)
 
 </br>
 
- View that allows saving favorite actors and directors
 ![userFavorite](https://user-images.githubusercontent.com/84510840/168163104-8d82a8f0-cf4a-45ea-9ab0-7bad41528ac1.png)
