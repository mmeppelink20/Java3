package meppelink.fun_stuff;

import com.neovisionaries.i18n.CountryCode;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class MySpotify { // model, used to obtain data
    private static String accessToken;
    public static String getAccessToken() {
        Dotenv dotenv = Dotenv.load();
        String clientId = dotenv.get("SPOTIFY_CLIENT_ID");
        String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET");
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();
        ClientCredentials clientCredentials = null;
        try {
            clientCredentials = clientCredentialsRequest.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SpotifyWebApiException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        accessToken = clientCredentials.getAccessToken();
        return accessToken;
    }

    public static Artist[] searchArtists(String q) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken())
                .build();
        SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(q)
                .market(CountryCode.US)

//          .limit(10)
//          .offset(0)
//          .includeExternal("audio")
                .build();
        Artist[] artists = null;
        try {
            final CompletableFuture<Paging<Artist>> pagingFuture = searchArtistsRequest.executeAsync();

            final Paging<Artist> artistPaging = pagingFuture.join();
            artists = artistPaging.getItems();
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
        return artists;
    }

    // get track

    // get album
    public static AlbumSimplified[] getArtistsAlbums(String q) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken())
                .build();

        GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums(q).build();

        AlbumSimplified[] albums = null;
        try {
            final CompletableFuture<Paging<AlbumSimplified>> pagingFuture = getArtistsAlbumsRequest.executeAsync();

            final Paging<AlbumSimplified> albumPaging = pagingFuture.join();
            albums = albumPaging.getItems();
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
        return albums;
    }

    public static Artist getArtistByArtistID(String artistID) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken())
                .build();
        GetArtistRequest getArtistRequest = spotifyApi.getArtist(artistID).build();

        Artist artist = null;
        try {
            final CompletableFuture<Artist> artistFuture = getArtistRequest.executeAsync();
            artist = artistFuture.join();
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
        return artist;
    }

    public static TrackSimplified[] getAlbumTracksByAlbumID(String albumID) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(getAccessToken())
                .build();
        GetAlbumsTracksRequest getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(albumID).build();

        TrackSimplified[] tracks = null;
        try {
            final CompletableFuture<Paging<TrackSimplified>> pagingFuture = getAlbumsTracksRequest.executeAsync();

            final Paging<TrackSimplified> trackPaging = pagingFuture.join();
            tracks = trackPaging.getItems();
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
        return tracks;
    }



    // return data to controller
}