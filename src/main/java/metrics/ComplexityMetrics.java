package metrics;

public class ComplexityMetrics {
    private final String algorithmName;
    private long bestCaseTime;
    private long worstCaseTime;
    private long averageCaseTime;
    private long memoryUsage;

    public ComplexityMetrics(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() { return algorithmName; }
    public long getBestCaseTime() { return bestCaseTime; }
    public void setBestCaseTime(long bestCaseTime) { this.bestCaseTime = bestCaseTime; }
    public long getWorstCaseTime() { return worstCaseTime; }
    public void setWorstCaseTime(long worstCaseTime) { this.worstCaseTime = worstCaseTime; }
    public long getAverageCaseTime() { return averageCaseTime; }
    public void setAverageCaseTime(long averageCaseTime) { this.averageCaseTime = averageCaseTime; }
    public long getMemoryUsage() { return memoryUsage; }
    public void setMemoryUsage(long memoryUsage) { this.memoryUsage = memoryUsage; }

    @Override
    public String toString() {
        return String.format(
                "ComplexityMetrics{algorithm=%s, bestCase=%d, worstCase=%d, averageCase=%d, memory=%d}",
                algorithmName, bestCaseTime, worstCaseTime, averageCaseTime, memoryUsage
        );
    }
}