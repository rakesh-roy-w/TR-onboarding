package day11;

public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer pc1 = new Computer.ComputerBuilder("32GB", "1TB", "Windows").setBluetoothEnabled(true).setHasTouchScreen(false).build();
        System.out.println("pc1 : " + pc1);
    }
}

class Computer {
    // mandatory fields
    private String RAM;
    private String HDD;
    private String OS;
    // optional field
    private boolean isBluetoothEnabled;
    private boolean hasTouchScreen;

    private Computer(ComputerBuilder builder){
        this.RAM = builder.RAM;
        this.HDD = builder.HDD;
        this.OS = builder.OS;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
        this.hasTouchScreen = builder.hasTouchScreen;
    }

    public String getRAM() {
        return RAM;
    }

    public String getHDD() {
        return HDD;
    }

    public String getOS() {
        return OS;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    public boolean isHasTouchScreen() {
        return hasTouchScreen;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "RAM='" + RAM + '\'' +
                ", HDD='" + HDD + '\'' +
                ", OS='" + OS + '\'' +
                ", isBluetoothEnabled=" + isBluetoothEnabled +
                ", hasTouchScreen=" + hasTouchScreen +
                '}';
    }
    //getters for fields

    public static class ComputerBuilder {
        // mandatory fields
        private String RAM;
        private String HDD;
        private String OS;
        // optional field
        private boolean isBluetoothEnabled;
        private boolean hasTouchScreen;

        public ComputerBuilder(String RAM, String HDD, String OS) {
            if (RAM == null || HDD == null || OS == null) {
                throw new IllegalArgumentException("RAM, HDD and OS cannot be null");
            }
            this.RAM = RAM;
            this.HDD = HDD;
            this.OS = OS;
        }
        // setter for optional fields
        public ComputerBuilder setBluetoothEnabled(boolean bluetoothEnabled) {
            this.isBluetoothEnabled = bluetoothEnabled;
            return this;
        }

        public ComputerBuilder setHasTouchScreen(boolean hasTouchScreen) {
            this.hasTouchScreen = hasTouchScreen;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

}
