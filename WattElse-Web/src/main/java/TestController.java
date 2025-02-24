import fr.eql.ai116.linus.wattelse.business.CalendarBusiness;
import fr.eql.ai116.linus.wattelse.business.ConnectionBusiness;
import fr.eql.ai116.linus.wattelse.business.CreditCardBusiness;
import fr.eql.ai116.linus.wattelse.business.ReservationBusiness;
import fr.eql.ai116.linus.wattelse.business.StationBusiness;
import fr.eql.ai116.linus.wattelse.business.exceptions.AuthenticationException;
import fr.eql.ai116.linus.wattelse.business.SecurityBusiness;
import fr.eql.ai116.linus.wattelse.business.exceptions.AuthorizationException;
import fr.eql.ai116.linus.wattelse.business.exceptions.UserRegistrationException;
import fr.eql.ai116.linus.wattelse.entity.pojo.Calendar;
import fr.eql.ai116.linus.wattelse.entity.Role;
import fr.eql.ai116.linus.wattelse.entity.dto.AuthenticateInDto;
import fr.eql.ai116.linus.wattelse.entity.dto.RegisterReservationInDto;
import fr.eql.ai116.linus.wattelse.entity.dto.RegisterUserInDto;
import fr.eql.ai116.linus.wattelse.entity.dto.AuthenticateOutDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Stateless
@Path("/test")
public class TestController {

    @EJB
    SecurityBusiness securityBusiness;

    @EJB
    ConnectionBusiness connectionBusiness;

    @EJB
    StationBusiness stationBusiness;

    @EJB
    ReservationBusiness reservationBusiness;

    @EJB
    CreditCardBusiness creditCardBusiness;

    @EJB
    CalendarBusiness calendarBusiness;

    // http://127.0.0.1:8080/api/rest/test/ping
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong !";
    }

    @GET
    @Path("/authenticate/incorrect")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateIncorrect() {
        try {
            AuthenticateOutDto dto_out = securityBusiness.authenticate("email", "password");
            return Response.ok(dto_out).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/authenticate/correct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateCorrect() {
        try {
            AuthenticateOutDto dto_out = securityBusiness.authenticate("a@b.c", "superMDP");
            return Response.ok(dto_out).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/register/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(RegisterUserInDto dto_in) {
        try {
            //RegisterUserInDto dto_in = new RegisterUserInDto("aled@b.c","superMDP","Thomas","Duron","0123456789", LocalDate.of(2000,7,31),0, 0,"de la pépinière, Villepinte, 93420, France");
            connectionBusiness.registerNewUser(dto_in.getEmail(), dto_in.getPassword(), dto_in.getFirstName(),
                    dto_in.getLastName(), dto_in.getPhone(), dto_in.getBirthDate(), dto_in.getLatitude(),
                    dto_in.getLongitude(), dto_in.getAddressDisplay());
            return Response.ok("oui").build();
        } catch (UserRegistrationException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
/*
    @GET
    @Path("/register/station")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerStation() {
        try {
            RegisterStationInDto dto_in = new RegisterStationInDto("bob", 13L, 1, 1L, 1L, 0D,0D, "21 rue normale, normal city, 1111, France");
            stationBusiness.registerNewStation(dto_in);
            return Response.ok("oui").build();
        } catch (StationRegistrationException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }*/

    @GET
    @Path("/reservation/{id}/start")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startCharge(@PathParam("id") long reservationId) {
        reservationBusiness.startCharging(reservationId);
        return Response.ok("oui").build();
    }

    @GET
    @Path("/reservation/{id}/end")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endCharge(@PathParam("id") long reservationId) {
        reservationBusiness.endCharging(reservationId);
        return Response.ok("oui").build();
    }


    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(AuthenticateInDto dto_in) {
        System.out.println(dto_in);
        try {
            AuthenticateOutDto dto_out = securityBusiness.authenticate(dto_in.getEmail(), dto_in.getPassword());
            return Response.ok(dto_out).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/register/reservation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerReservation() {
        RegisterReservationInDto dto_in = new RegisterReservationInDto("",1L, 1L, LocalDate.now().toString(), 1, 1, 1, 1);
        reservationBusiness.registerNewReservation(dto_in.getToken(), dto_in.getIdVehicule(), dto_in.getIdStation(), LocalDate.parse(dto_in.getDateReservation()), dto_in.getIdHeureDebutReservation(), dto_in.getIdMinuteDebutReservation(), dto_in.getIdHeureFinReservation(), dto_in.getIdMinuteFinReservation());
        return Response.ok("oui").build();
    }

    @GET
    @Path("/authorize")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize() {
        try {
            securityBusiness.authorize("Bearer " + securityBusiness.authenticate("a@b.c", "superMDP").getToken(), Role.USER);
            return Response.ok("ok").build();
        } catch (AuthorizationException | AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/station/{id}/calendar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCalendar(@PathParam("id") long stationId) {
        Calendar calendar = calendarBusiness.getStationCalendar(stationId);
        return Response.ok(calendar).build();
    }
}