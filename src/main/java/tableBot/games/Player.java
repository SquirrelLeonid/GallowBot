package tableBot.games;

public class Player
{
    private String tag;

    public Player (String tag)
    {
        this.tag = tag;
    }

    public String getPlayerTag ()
    {
        return tag;
    }

    @Override
    public boolean equals (Object object)
    {
        if (object == this)
            return true;
        if (object == null || object.getClass() != this.getClass())
            return false;
        Player player = (Player) object;
        return player.getPlayerTag().compareTo(tag) == 0;
    }

    @Override
    public int hashCode ()
    {
        return tag.hashCode();
    }
}
