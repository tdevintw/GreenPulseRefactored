package Services;

import Domain.User;

public interface ReportService {
    void generateDailyReport(User user);

    void generateWeeklyReport(User user);

    void generateMonthlyReport(User user);

}
