/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS" NTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.artesanias.exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@ApplicationException( rollback = true )
public class BusinessLogicException extends Exception implements Serializable
{
	private Response.Status status;
	
	public BusinessLogicException( Response.Status status )
	{
		super( );
		this.status = status;
	}
	
	public BusinessLogicException( String message, Response.Status status )
	{
		super( message );
		this.status = status;
	}
	
	public BusinessLogicException( Throwable cause, Response.Status status )
	{
		super( cause );
		this.status = status;
	}
	
	public BusinessLogicException( String message, Throwable cause )
	{
		super( message, cause );
	}
	
	/**
	 * Retrieves the status of the BusinessLogicException
	 *
	 * @return The status of the BusinessLogicException
	 */
	public Response.Status getStatus( )
	{
		return status;
	}
}