
import fr.eql.ai116.linus.wattelse.business.ReservationBusiness;
import fr.eql.ai116.linus.wattelse.business.SpaceBusiness;
import fr.eql.ai116.linus.wattelse.entity.dto.FullReservationDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Rating;
import fr.eql.ai116.linus.wattelse.entity.pojo.Reservation;
import fr.eql.ai116.linus.wattelse.entity.dto.VehicleDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/space")
public class SpaceController {

    @EJB
    SpaceBusiness spaceBusiness;

    @EJB
    ReservationBusiness reservationBusiness;

    @GET
    @Path("/user/{id}/vehicles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchUsersVehicles(@PathParam("id") long userId) {
        List<VehicleDto> vehiclesDto = spaceBusiness.fetchUsersVehicles(userId);
        return Response.ok(vehiclesDto).build();
    }

    @POST
    @Path("/addmodvehicle")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVehicle(VehicleDto vehicleDto) {
        spaceBusiness.addModVehicle(vehicleDto);
        return Response.ok().build();
    }

    @POST
    @Path("/suppvehicle/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response suppVehicle(@PathParam("id") long vehicleId) {
        spaceBusiness.suppVehicle(vehicleId);
        return Response.ok().build();
    }

    @GET
    @Path("/user/{id}/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchUsersReservations(@PathParam("id") long userId) {
        List<FullReservationDto> fullReservationDtos = reservationBusiness.fetchUserFullReservations(userId);
        return Response.ok(fullReservationDtos).build();
    }

    @POST
    @Path("/user/updatereservations")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReservation(Reservation reservation) {
        System.out.println(reservation);
        reservationBusiness.updateReservation(reservation);
        return Response.ok().build();
    }

    @POST
    @Path("/reservation/{id}/startcharge")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response startCharging(@PathParam("id") long reservationId) {
        reservationBusiness.startCharging(reservationId);
        return Response.ok().build();
    }

    @POST
    @Path("/reservation/{id}/endcharge")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response endCharging(@PathParam("id") long reservationId) {
        reservationBusiness.endCharging(reservationId);
        return Response.ok().build();
    }

    @POST
    @Path("/reservation/rate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ratingReservation(Rating rating) {
        System.out.println(rating);
        Long ratingId = reservationBusiness.ratingReservation(rating);
        System.out.println(ratingId);
        return Response.ok(ratingId).build();
    }
}
