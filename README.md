# movieDB
Android Movie DB
----------------
Movie DB Application for Android written in Java using MVVM architeture. Application shows list of films based on popularity in decending order. The list allows the user to infinitely scroll and it lazily loads more items by makeing extra network requests as needed. Tapping on a movie on the list bring the user to a detail screen showing some more information about that movie.

Uses Retrofit for network calls.
RxJava to observe Retrofit calls.
Views observe ViewModels using LiveData objects.


To Do
-----
Saving items as favourites
Work offline
Sharing items
Filtering
Transitions

Future Work
-----------
Incorporate Android Paging Library to use a repository to populate RecyclerView list with pages of data from a Room db and also backfill the db with new information from the network when the user scrolls through all the pages of data in the db in the list.

This would achieve offline storage and also automatically implement infinite scroll rather than having to calculate the scroll position in the list as currently done.

