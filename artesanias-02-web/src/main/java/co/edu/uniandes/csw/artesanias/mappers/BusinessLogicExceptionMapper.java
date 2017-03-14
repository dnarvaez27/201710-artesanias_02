package co.edu.uniandes.csw.artesanias.mappers;

import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper de Excepciones ocurridas en el sistema
 *
 * @author d.narvaez11
 * @see BusinessLogicException
 */
@Provider
public class BusinessLogicExceptionMapper implements ExceptionMapper<BusinessLogicException>
{
	@Override
	public Response toResponse( BusinessLogicException exception )
	{
		return Response.status( exception.getStatus( ) )
		               .entity( exception.getMessage( ) )
		               .type( "text/plain" )
		               .build( );
	}
}