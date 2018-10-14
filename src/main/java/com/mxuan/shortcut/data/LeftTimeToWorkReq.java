package com.mxuan.shortcut.data;

import java.util.List;

public class LeftTimeToWorkReq {
    static class EventInfo {
        String name;
        int time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "EventInfo{" +
                    "name='" + name + '\'' +
                    ", time=" + time +
                    '}';
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    private List<EventInfo> eventInfos;

    public List<EventInfo> getEventInfos() {
        return eventInfos;
    }

    @Override
    public String toString() {
        return "LeftTimeToWorkReq{" +
                "eventInfos=" + eventInfos +
                '}';
    }

    public void setEventInfos(List<EventInfo> eventInfos) {
        this.eventInfos = eventInfos;
    }
}
