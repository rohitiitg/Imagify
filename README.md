Android Studio : Imagify App

This is a simple App which have few animated photos as its main activity . It allow the user to see any image in full view by clicking on it and then upload it in the SQLite server (internal sever for android).User can also view the uploaded images and delete them .

Following processes take place when image is uploaded

1. compress to byte stream.

 2 encode into a string.

 3 encrypt using AES algorithm.

 4 store to server as a string.

Reverse processes takes place when image is retrieved from the server.

