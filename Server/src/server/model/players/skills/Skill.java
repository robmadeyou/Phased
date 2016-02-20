package server.model.players.skills;

import server.model.players.Client;

public class Skill {

    private Client client;

    public Skill( Client c ) {
        super();
        this.client = c;
    }

    public Client getClient( )
    {
        return this.client;
    }

    public Skill setClient( Client c )
    {
        this.client = c;
        return this;
    }

    public Skill execute()
    {
        return this;
    }

    public Skill
}
