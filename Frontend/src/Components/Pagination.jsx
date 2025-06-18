export default function Pagination({ pageParam, changePage, totalPages }) {
  // Calcolo intervallo centrale
  const startPage = Math.max(1, pageParam - 2);
  const endPage = Math.min(totalPages - 2, pageParam + 2);

  // Sequenza di pagine centrali
  const pages = [];
  for (let i = startPage; i <= endPage; i++) {
    pages.push(i);
  }

  return (
    <nav aria-label="Page navigation example" className="my-5">
      <ul className="pagination justify-content-center">

        {/* Previous */}
        <li className={`page-item ${pageParam === 0 ? 'disabled' : ''}`}>
          <button
            className="page-link"
            onClick={() => changePage(pageParam - 1)}
            disabled={pageParam === 0}
          >
            Previous
          </button>
        </li>

        {/* Prima pagina sempre visibile */}
        <li className={`page-item ${pageParam === 0 ? 'active' : ''}`}>
          <button className="page-link" onClick={() => changePage(0)}>1</button>
        </li>

        {/* Puntini prima del gruppo centrale */}
        {pageParam > 3 && (
          <li className="page-item disabled">
            <span className="page-link">...</span>
          </li>
        )}

        {/* Pagine vicine alla pagina corrente */}
        {pages.map((pageNumber) => (
          <li
            key={pageNumber}
            className={`page-item ${pageNumber === pageParam ? 'active' : ''}`}
          >
            <button className="page-link" onClick={() => changePage(pageNumber)}>
              {pageNumber + 1}
            </button>
          </li>
        ))}

        {/* Puntini dopo il gruppo centrale */}
        {pageParam < totalPages - 4 && (
          <li className="page-item disabled">
            <span className="page-link">...</span>
          </li>
        )}

        {/* Ultima pagina sempre visibile */}
        {totalPages > 1 && (
          <li className={`page-item ${pageParam === totalPages - 1 ? 'active' : ''}`}>
            <button className="page-link" onClick={() => changePage(totalPages - 1)}>
              {totalPages}
            </button>
          </li>
        )}

        {/* Next */}
        <li className={`page-item ${pageParam === totalPages - 1 ? 'disabled' : ''}`}>
          <button
            className="page-link"
            onClick={() => changePage(pageParam + 1)}
            disabled={pageParam === totalPages - 1}
          >
            Next
          </button>
        </li>

      </ul>
    </nav>
  );
}
