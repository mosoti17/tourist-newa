# Tourist News

An application to demonstrate MVVM, jetpack compose, and Pagging 3 with offline disk cache

## Architecture
The app uses Model, View, ViewModel architecture (Model <--> ViewModel <--> View) as it provides an easy way to develop and maintain functionality by separating view logic from business/model logic.\
The repository pattern was not used as the RemoteMediator handles the logic of pulling the data from the remote source or local cache when the API is unavailable.\
Room was used as an offline cache as it provides the implementation of a paging source and can be used in Paging 3 together with a RemoteMediator to pull data from the API cache locally before displaying it to the user and only refreshing from the remote source when necessary.


## Third-party libraries
- Network access - Retrofit with Moshi as the converter factory
- Image loading - Coil

## License

[MIT](https://choosealicense.com/licenses/mit/)