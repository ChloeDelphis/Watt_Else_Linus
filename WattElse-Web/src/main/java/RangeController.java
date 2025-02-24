import fr.eql.ai116.linus.wattelse.business.RangeBusiness;
import fr.eql.ai116.linus.wattelse.entity.range.AccountClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.range.BanMotive;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.RatingType;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationAnormalEnding;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationHour;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationMinute;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.StationClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.range.StationService;
import fr.eql.ai116.linus.wattelse.entity.range.TypeTarification;
import fr.eql.ai116.linus.wattelse.entity.range.WeekDay;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Path("/range")
public class RangeController {


    @EJB
    RangeBusiness rangeBusiness;

    @GET
    @Path("/fetch/sockets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchSockets() {
        List<Socket> sockets = rangeBusiness.fetchSockets();
        return Response.ok(sockets).build();
    }
    @GET
    @Path("/fetch/powers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchPower() {
        List<Power> powers = rangeBusiness.fetchPower();
        return Response.ok(powers).build();
    }
    @GET
    @Path("/fetch/tarifications")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchTarification() {
        List<TypeTarification> tarifications = rangeBusiness.fetchTarification();
        return Response.ok(tarifications).build();
    }

    @GET
    @Path("/fetch/reservationHour")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchReservationHour() {
        List<ReservationHour> reservationHours = rangeBusiness.fetchReservationHour();
        return Response.ok(reservationHours).build();
    }
    @GET
    @Path("/fetch/reservationMinutes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchReservationMinutes() {
        List<ReservationMinute> reservationMinutes = rangeBusiness.fetchReservationMinute();
        return Response.ok(reservationMinutes).build();
    }
    @GET
    @Path("/fetch/weekday")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchWeekDay() {
        List<WeekDay> weekDays = rangeBusiness.fetchWeekDay();
        return Response.ok(weekDays).build();
    }

    @GET
    @Path("/fetch/banmotives")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchBanMotive () {
        List<BanMotive> banMotives = rangeBusiness.fetchBanMotive();
        return Response.ok(banMotives).build();
    }
    @GET
    @Path("/fetch/stationclosingmotive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchStationClosingMotive() {
        List<StationClosingMotive> stationClosingMotives = rangeBusiness.fetchStationClosingMotive();
        return Response.ok(stationClosingMotives).build();
    }
    @GET
    @Path("/fetch/accountclosingmotive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchAccountClosingMotive() {
        List<AccountClosingMotive> accountClosingMotives = rangeBusiness.fetchAccountClosingMotive();
        return Response.ok(accountClosingMotives).build();
    }

    @GET
    @Path("/fetch/reservation/anormal/Ending")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchReservationAnormalEnding() {
        List<ReservationAnormalEnding> reservationAnormalEndings = rangeBusiness.fetchReservationAnormalEnding();
        return Response.ok(reservationAnormalEndings).build();
    }
    @GET
    @Path("/fetch/ratingtype")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRatingType() {
        List<RatingType> ratingTypes = rangeBusiness.fetchRatingType();
        return Response.ok(ratingTypes).build();
    }
    @GET
    @Path("/fetch/stationservice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchStationService() {
        List<StationService> stationServices = rangeBusiness.fetchStationService();
        return Response.ok(stationServices).build();
    }

}

