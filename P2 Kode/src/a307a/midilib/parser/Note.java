package a307a.midilib.parser;

class Note implements INote {
    private int pitch;
    private int velocity;
    private long tick;

    public Note(BufferedMidiEvent event) {
        this(event.getPitch(), event.getVelocity(), event.getTick());
    }

    public Note(int pitch, int velocity, long tick) {
        this.pitch = pitch;
        this.velocity = velocity;
        this.tick = tick;
    }

    @Override
    public int getPitch() {
        return pitch;
    }

    @Override
    public int getVelocity() {
        return velocity;
    }

    @Override
    public long getTick() {
        return tick;
    }

    @Override
    public INote clone() {
        return new Note(this.pitch, this.velocity, this.tick);
    }
}
