package a307a.midilib.parser;

import javax.sound.midi.*;
import javax.sound.midi.spi.MidiFileReader;
import java.io.*;
import java.util.*;

public class Melody implements IMelody{
    public double getBeat(INote note){
        return 0;
    }

    public Set<Integer> getChannels(File file) throws IOException, InvalidMidiDataException {
        Set<Integer> channelSet = new TreeSet<>();
        Sequence midiSequence = MidiSystem.getSequence(file);
        Track[] trackStorage = midiSequence.getTracks();
        int channelHolder;

        for(int trackCount = 0; trackCount < trackStorage.length; ++trackCount){
            for(int eventCount = 0; eventCount < trackStorage[trackCount].size(); ++eventCount){
                if((trackStorage[trackCount].get(eventCount).getMessage().getStatus() / 0x10) == 9){
                    channelSet.add(trackStorage[trackCount].get(eventCount).getMessage().getStatus() % 0x10);
                }
            }
        }


        return channelSet;
    }

    public List<Integer> getIntervals(){
        return null;
    }

    @Override
    public Iterator<INote> iterator() {
        return null;
    }
}
