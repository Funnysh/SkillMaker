const API_URL = "http://localhost:8080";

const fetchData = (url, requestOptions = {}) => {
  const apiUrl = `${API_URL}${url}`;
  const token = localStorage.getItem("token");

  const headers = {
    "Content-Type": "application/json",
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
    ...(requestOptions.headers || {}),
  };

  return fetch(apiUrl, { ...requestOptions, headers })
    .then(async (response) => {
      if (!response.ok) {
        if (response.status === 401) {
          localStorage.removeItem("token");
        }
        const text = await response.text().catch(() => "");
        throw new Error(`${response.status} ${response.statusText} ${text}`);
      }
      if (requestOptions.method !== "DELETE") return response.json();
    });
};


export const apiGet = (url, params) => {
    const filtered = Object.fromEntries(
        Object.entries(params || {}).filter(([_, v]) => v != null && v !== "")
    );
    const qs = Object.keys(filtered).length ? `?${new URLSearchParams(filtered)}` : "";
    
    return fetchData(`${url}${qs}`, { method: "GET" });
};

export const apiPost = (url, data) => {
    const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
    };

    return fetchData(url, requestOptions);
};

export const apiPut = (url, data) => {
    const requestOptions = {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
    };

    return fetchData(url, requestOptions);
};

export const apiDelete = (url) => {
    const requestOptions = {
        method: "DELETE",
    };

    return fetchData(url, requestOptions);
};
