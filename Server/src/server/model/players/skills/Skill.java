package server.model.players.skills;

import server.model.items.Item;
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

    protected interface ObjectTraining
    {
        /**
         * Triggered when an item is used on an object
         * @param item Item
         * @param object server.model.objects.Object
         */
        void useItemOnObject( Item item, server.model.objects.Object object );
    }

    protected interface ItemTraining
    {
        /**
         * What happens when an item is used on another item
         * @param usedItem Item
         * @param onItem Item
         */
        void useItemOnItem( Item usedItem, Item onItem );

        void useItem();
    }
}
