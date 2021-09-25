package study.datajpa.repository;

public interface NestedClosedProjections {

    /**
     * root 는 정확하게 가져옴 (여기선 getUsername)
     * 그 아래부터는 엔티티를 가져와서 거기서 계산 함. (전체 다 가져옴. 최적화 안됨.)
     */
    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
