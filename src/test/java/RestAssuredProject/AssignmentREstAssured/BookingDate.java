package RestAssuredProject.AssignmentREstAssured;

public class BookingDate {
    private String checkin;
    private String checkout;

    @Override
    public String toString() {
        return "BookingDate{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }

    public String getCheckin(String s) {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout(String s) {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

}
