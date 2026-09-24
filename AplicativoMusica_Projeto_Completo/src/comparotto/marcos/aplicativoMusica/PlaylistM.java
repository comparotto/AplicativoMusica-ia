package comparotto.marcos.aplicativoMusica;

public class PlaylistM {
    private int playlistId;
    private int musicaId;

    public PlaylistM() {
    }

    public PlaylistM(int playlistId, int musicaId) {
        this.playlistId = playlistId;
        this.musicaId = musicaId;
    }


    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(int musicaId) {
        this.musicaId = musicaId;
    }
}