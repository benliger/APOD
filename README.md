# APOD

Project using NASA APOD (Astronomy Picture of the Day) API (see https://api.nasa.gov/)

To make the project compile, you will need to set your NASA API key to it.
To do that just add *nasaApiKey=YOUR-OWN-KEY* in your global *gradle.properties* (file in *USER_HOME/.gradle*) to make the project compile

*Features*
* display the last 30 APOD pictures in a list (or a grid)
* display detail of an APOD
* display fullscreen HD APOD picture in a "photo view"
* dark mode

*Evolution ideas:*
* Save APOD data into database
* Add a CI
* Use Coroutine instead of RxJava
* Add APOD video integration
