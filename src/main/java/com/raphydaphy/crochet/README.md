# Crochet


Crochet is a simple Fabric library mod which provides Networking & Entity Data tools for other mods to use. By itself, it includes no ingame content.

## Crochet Packets
Creating an sending packets with Crochet is easy! Simply implement `IPacket` on your packet class, and create a Handler class which extends `MessageHandler<YourPacketClass`.  A simple packet which sends an integer could look like this.


``````
public class DemoPacket implements IPacket
{
    public static final Identifier ID = new Identifier("example_mod", "demo");
    private int number;
    
    private DemoPacket() { }
    
    public DemoPacket(int number)
    {
        this.number = number;
    }
    
    @Override
    public void read(PacketByteBuf buf)
    {
        number = buf.readInt();
    }
    
    @Override
    public void write(PacketByteBuf buf)
    {
        buf.writeInt(number);
    }
    
    @Override
    public Identifier getID()
    {
        return ID;
    }
    
    public static class Handler extends MessageHandler<DemoPacket>
    {
        @Override
        protected DemoPacket create()
        {
            return new DemoPacket();
        }
    
        @Override
        public void handle(PacketContext ctx, DemoPacket message)
        {
            System.out.println("The number is " + message.number);
        }
    }
}

``````

You need to register the packet on the side which will be receiving it. Client-bound packets should be registered in `ClientModInitializer#onInitializeClient` while server-bound packets can be registered in `ModInitializer#onInitialize`. See the example below for an example of packet registration.

`````
// For Client-Bound Packets
ClientSidePacketRegistry.INSTANCE.register(DemoPacket.ID, new DemoPacket.Handler());

// For Server-Bound Packets
ServerSidePacketRegistry.INSTANCE.register(DemoPacket.ID, new DemoPacket.Handler());
`````

## Crochet Entity-Data
Crochet also provides tools that allow mod developers to store persistent data on players. Data is stored in `CompoundTag` and sent to the client whenever it is marked as dirty. Therefore, data should be added on the server-side, although adding it on both sides will also work. Below is a simple example of the Crochet PlayerData system being used to store a boolean.

``````
public boolean onActivated(PlayerEntity player)
{
    PlayerData.get(player).putBoolean("UsingItem", true);
    PlayerData.markDirty(player);
}
``````