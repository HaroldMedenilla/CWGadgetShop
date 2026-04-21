public class MP3 extends Gadget
{
    private int memory;
    
    public MP3(String model, double price, int weight, String size, int memory)
    {
        super(model, price, weight, size);
        this.memory = memory;
    }
    
    public int getMemory()
    {
        return memory;
    }
    
    public void downloadMusic(int memSize)
    {
        if(memSize <= memory)
        {
            memory -= memSize;
            System.out.println("Download successful!");
        }
        else
        {
            System.out.println("Download unsuccessful. Insufficient memory.");
        }
    }
    
    public void deleteMusic(int memSize)
    {
        memory += memSize;
        System.out.println("Music deleted. Memory restored.");
    }
    
    public void display()
    {
        super.display();
        System.out.println("Available memory: " + memory + " MB");
    }
}