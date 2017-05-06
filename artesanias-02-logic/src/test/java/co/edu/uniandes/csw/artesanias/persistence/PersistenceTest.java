/*
 * The MIT License
 *
 * Copyright 2017 d.narvaez11.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import org.junit.Before;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
public abstract class PersistenceTest<T>
{
	List<T> data = new LinkedList<>( );
	
	@PersistenceContext( unitName = "artesaniasPU" )
	EntityManager em;
	
	@Inject
	protected UserTransaction utx;
	
	@Before
	public void setUp( )
	{
		try
		{
			utx.begin( );
			em.joinTransaction( );
			clearData( );
			insertData( );
			utx.commit( );
		}
		catch( Exception e )
		{
			try
			{
				utx.rollback( );
			}
			catch( SystemException e1 )
			{
				e1.printStackTrace( );
			}
		}
	}
	
	protected abstract void clearData( );
	
	protected abstract void insertData( );
}